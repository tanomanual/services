package org.inneo.services.servicos;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.inneo.services.domain.dtos.HistoriaResponse;
import org.inneo.services.domain.enums.Situacao;
import org.inneo.services.domain.historia.Historia;
import org.springframework.data.domain.Pageable;
import org.inneo.services.repository.HistoriaRep;
import org.inneo.services.domain.specs.PostagenSpec;

@Service
@RequiredArgsConstructor
public class HistoriaService {
	private static final Logger logger = LoggerFactory.getLogger(HistoriaService.class);
	private final HistoriaRep  historiaRep;
	private final UsuarioService usuarioService;
	
	public Historia publicar(Historia request) {
		Historia historia = request.getUuid() != null ? historiaRep.findById(request.getUuid()).get() : new Historia();		
		logger.info("Historia publicada com sucesso.");
		request.setPublished(historia.getPublished());		
		request.setUsuario(usuarioService.getLogado());
		BeanUtils.copyProperties(request, historia);	
		return historiaRep.save(historia);
	}
	
	public void delete(UUID uuid) {	
		logger.info("Historia deletada com sucesso.");
		Historia historia = historiaRep.findById(uuid).get();
		historia.setDataInativacao(new Date());
		historiaRep.save(historia);
	}
	
	public Page<HistoriaResponse> postagens(Pageable pageable) {
		logger.info("Historias encontareadas.");
		Page<Historia> postagens = historiaRep.findAll(PostagenSpec.doSituacao(Situacao.ATIVO), pageable);
		List<HistoriaResponse> postagem = postagens.stream().map(HistoriaResponse::new).toList();		 
		return new PageImpl<HistoriaResponse>(postagem, pageable, postagens.getTotalElements());
	
	}
}
