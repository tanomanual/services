package org.inneo.services.servicos;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.RequiredArgsConstructor;

import org.inneo.services.domain.enums.Situacao;
import org.inneo.services.domain.feed.Post;
import org.inneo.services.domain.specs.PostSpec;
import org.inneo.services.repository.PostRep;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.inneo.services.domain.usuario.Login;

@Service
@RequiredArgsConstructor
public class PostService {
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	private final PostRep  postRep;
	private final LoginService loginServer;
	
	public Post publicar(Post request) {
		Post post = request.getUuid() != null ? postRep.findById(request.getUuid()).get() : new Post();		
		logger.info("Postagem cadastrada ou atualizada com sucesso.");
		request.setPublished(post.getPublished());		
		Login login = loginServer.getLogado();
		
		BeanUtils.copyProperties(request, post);		
		post.setUsername(login.getUsername());
		return postRep.save(post);
	}
	
	public void delete(UUID uuid) {	
		logger.info("Postagem deletada com sucesso.");
		Post post = postRep.findById(uuid).get();
		post.setDataInativacao(new Date());
		postRep.save(post);
	}
	
	public List<Post> feeds() {
		return postRep.findAll(
				PostSpec.doSituacao(Situacao.ATIVO)
		);
	}
	
	

}
