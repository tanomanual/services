package org.inneo.services.servicos;

import java.util.Date;
import java.util.UUID;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.inneo.services.domain.usuario.Login;
import org.inneo.services.domain.feed.Postagem;

import org.inneo.services.domain.enums.Situacao;
import org.springframework.data.domain.Pageable;
import org.inneo.services.repository.PostagemRep;
import org.inneo.services.domain.specs.PostagenSpec;

@Service
@RequiredArgsConstructor
public class PostagemService {
	private static final Logger logger = LoggerFactory.getLogger(PostagemService.class);
	private final PostagemRep  postagemRep;
	private final LoginService loginServer;
	
	public Postagem publicar(Postagem request) {
		Postagem postagem = request.getUuid() != null ? postagemRep.findById(request.getUuid()).get() : new Postagem();		
		logger.info("Postagem cadastrada ou atualizada com sucesso.");
		request.setPublished(postagem.getPublished());		
		Login login = loginServer.getLogado();
		
		BeanUtils.copyProperties(request, postagem);		
		postagem.setLoginId(login.getUuid());
		return postagemRep.save(postagem);
	}
	
	public void delete(UUID uuid) {	
		logger.info("Postagem deletada com sucesso.");
		Postagem postagem = postagemRep.findById(uuid).get();
		postagem.setDataInativacao(new Date());
		postagemRep.save(postagem);
	}
	
	public Page<Postagem> postagens(Pageable pageable) {
		return postagemRep.findAll(PostagenSpec.doSituacao(Situacao.ATIVO), pageable);
	}
	
	

}
