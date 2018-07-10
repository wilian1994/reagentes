package br.com.reagentes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.reagentes.models.Reagente;
import br.com.reagentes.models.Status;
import br.com.reagentes.service.ReagenteService;

@Controller
public class ReagenteController {

	@Autowired
	ReagenteService repository;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("reagentes/lista");
		Iterable<Reagente> reagentes = repository.findByStatus(Status.APROVADO);
		modelAndView.addObject("reagentes", reagentes);
		return modelAndView;
	}

	@RequestMapping("/form")
	public ModelAndView form(Optional<Reagente> reagente) {
		ModelAndView modelAndView = new ModelAndView("reagentes/form");
		modelAndView.addObject("reagente", reagente);
		return modelAndView;
	}

	@PostMapping("/salvar")
	public String salvar(Reagente reagente) {
		Authentication user = SecurityContextHolder.getContext().getAuthentication();
		Boolean userLogin = user.getAuthorities().toString().equals("[ROLE_USER]");
		
		if(userLogin) {
			reagente.setStatus(Status.APROVADO);
		}else {
			reagente.setStatus(Status.PENDENTE);
		}
		
		repository.save(reagente);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
		repository.delete(id);
		attributes.addFlashAttribute("mensagemDelete", "Reagente removido com sucesso!");
		return "redirect:/";
	}

	@GetMapping("edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		return form(repository.findOne(id));
	}

	@GetMapping("/carregaEdicaoReagente/{id}")
	@ResponseBody
	public String carregaEdicao(@PathVariable("id") Long id) throws JsonProcessingException {
		Optional<Reagente> reagente = repository.findOne(id);
		return new ObjectMapper().writeValueAsString(reagente.get());

	}

	@GetMapping("/buscaInfoReagente/{nome}")
	@ResponseBody
	public String buscaInfo(@PathVariable("nome") String nome) throws JsonProcessingException {
		Reagente reagente = new Reagente().buscaInfo(nome);
		return new ObjectMapper().writeValueAsString(reagente);

	}
	
	@RequestMapping("/contribuicoes")
	public ModelAndView contribuicoes() {
		ModelAndView modelAndView = new ModelAndView("reagentes/lista");
		Iterable<Reagente> reagentes = repository.findByStatus(Status.PENDENTE);
		modelAndView.addObject("reagentes", reagentes);
		modelAndView.addObject("contribuicao", "sim");
		return modelAndView;
	}
	
	@GetMapping("/aprovaContribuicao/{id}")
	public String aprovaContribuicao(@PathVariable("id") Long id) {
		return salvar(repository.findOne(id).get());
	}

}
