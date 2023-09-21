package org.inneo.services.servicos;

import org.inneo.services.domain.dtos.ComentarioResponse;
import org.inneo.services.domain.comentario.Comentario;
import org.inneo.services.repository.ComentarioRep;
import org.inneo.services.domain.historia.Historia;
import org.inneo.services.domain.specs.ComentarioSpec;
import org.inneo.services.repository.HistoriaRep;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.beans.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ComentarioService {
	private static final Logger logger = LoggerFactory.getLogger(HistoriaService.class);
	private final UsuarioService usuarioService;
	private final ComentarioRep comentarioRep;	
	private final HistoriaRep historiaRep;
	
	public Comentario comentar(Comentario request, UUID uuid) {
		Comentario comentario = request.getUuid() != null ? comentarioRep.findById(request.getUuid()).get() : new Comentario();	
		request.setPublished(comentario.getPublished());		
		request.setUsuario(usuarioService.getLogado());
		

		Historia historia = historiaRep.findById(uuid).get();
		System.out.println(historia.getConteudo());
		request.setHistoria(historia);		
		BeanUtils.copyProperties(request, comentario);	
		
		logger.info("Comentario publicada com sucesso.");
		comentario = comentarioRep.save(comentario);
		
		historia.getComentarios().add(comentario);
		historiaRep.save(historia);
		return comentario;
	}
	
	public Page<ComentarioResponse> postagens(UUID uuid, Pageable pageable) {		
		Page<Comentario> comentarios = comentarioRep.findAll(ComentarioSpec.daHistoria(uuid), pageable);
		logger.error("Listando comentarios. {}", comentarios.getContent());
		List<ComentarioResponse> comentario = comentarios.stream().map(ComentarioResponse::new).toList();
		return new PageImpl<ComentarioResponse>(comentario, pageable, comentarios.getTotalElements());			
	}

}
