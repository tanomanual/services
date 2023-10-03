package org.inneo.services.servicos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.AllArgsConstructor;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.inneo.services.domain.feed.Historia;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.inneo.services.domain.enums.Situacao;
import org.inneo.services.repository.HistoriaRep;
import org.inneo.services.domain.specs.HistoriaSpec;
import org.inneo.services.domain.dtos.HistoriaResponse;
import org.inneo.services.servicos.security.UsuarioService;

@Service
@AllArgsConstructor
public class HistoriaService {
	private static final Logger logger = LoggerFactory.getLogger(HistoriaService.class);
	private final UsuarioService usuarioService;
	private final HistoriaRep historiaRep;
	
	public HistoriaResponse save(Historia request) {
		Historia historia = request.getUuid() != null ? historiaRep.findById(request.getUuid()).get() : new Historia();		
		logger.info("Publicando conteudo, aguarde.");
		request.setComentario(false);
		request.setPublished(historia.getPublished());		
		request.setUsuario(usuarioService.getLogado());
		BeanUtils.copyProperties(request, historia);
		return new HistoriaResponse(historiaRep.save(historia));
	}
	
	public HistoriaResponse getID(UUID uuid) {
		Historia historia = historiaRep.findById(uuid).get();	
		Collection<Historia> comentarios = historiaRep.findAll(HistoriaSpec.daPublicacao(historia.getUuid()));	
		logger.info("Localizando historia {}, aguarde.", uuid);	
		historia.setPodeEditar(publicador(historia));
		historia.setComentaram(comentarios.size());
		historia.setComentario(historia.getComentario());
		historia.setComentarios(comentarios);
		return new HistoriaResponse(historia);
	}
	
	public HistoriaResponse delete(UUID uuid) {
		Historia historia = historiaRep.findById(uuid).get();		
		logger.info("Deletando historia {}, aguarde.", uuid);
		historia.setPodeEditar(publicador(historia));
		historia.setComentaram(historiaRep.findAll(HistoriaSpec.daPublicacao(uuid)).size());
		if(historia.getPodeEditar()) {
			historia.setDataInativacao(new Date());
			return new HistoriaResponse(historiaRep.save(historia));
		}
		throw new RuntimeException("Você não pode deletar essa publicação.");
		
	}
	
	public Page<HistoriaResponse> findAll(Pageable pageable) {	
		Page<Historia> paginas = historiaRep.findAll(HistoriaSpec.daSituacao(Situacao.ATIVO) , pageable);	
		Collection<Historia> historias = new ArrayList<>();
		for(Historia historia: paginas.getContent()) {	
			Collection<Historia> comentarios = historiaRep.findAll(HistoriaSpec.daPublicacao(historia.getUuid()));
			historia.setPodeEditar(publicador(historia));
			historia.setComentaram(comentarios.size());	
			historia.setComentarios(comentarios);
			historia.setComentario(historia.getComentario());
			if(!historia.getComentario()) {
				historias.add(historia);
			}
		}
		List<HistoriaResponse> response = historias.stream().map(HistoriaResponse::new).toList();
		
		return new PageImpl<HistoriaResponse>(response, pageable, paginas.getTotalElements());
	}
	
	
	public HistoriaResponse comentar(UUID uuid, Historia request) {
		Historia comentario = request.getUuid() != null ? historiaRep.findById(request.getUuid()).get() : new Historia();		
		logger.info("Publicando comentario, aguarde.");
		request.setConteudo(request.getConteudo());
		request.setPublicacao(uuid);
		request.setComentario(true);
		request.setPublished(comentario.getPublished());		
		request.setUsuario(usuarioService.getLogado());
		BeanUtils.copyProperties(request, comentario);
		return new HistoriaResponse(historiaRep.save(comentario));
	}	
	
	public Boolean publicador(Historia historia) {
		Boolean publicador = false;
		if(usuarioService.getLogado().getUuid().equals(historia.getUsuario().getUuid())){
			publicador = true;
		}
		return publicador;		
	}

}
