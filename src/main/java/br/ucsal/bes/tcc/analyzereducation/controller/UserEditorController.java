package br.ucsal.bes.tcc.analyzereducation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ucsal.bes.tcc.analyzereducation.analyzer.JavaCode;
import br.ucsal.bes.tcc.analyzereducation.dto.CodeEditorDTO;
import br.ucsal.bes.tcc.analyzereducation.model.CodeEditor;
import br.ucsal.bes.tcc.analyzereducation.repository.CodeEditorRepository;

@Controller
@RequestMapping("home")
public class UserEditorController {

	@Autowired
	private CodeEditorRepository codeEditorRepository;

	@GetMapping("usereditor")
	public String usereditor(CodeEditorDTO codeEditor) {
		return "home/usereditor";
	}

	@PostMapping("novo")
	public String novo(@Valid CodeEditorDTO codeEditorDto, BindingResult result) {

		if (result.hasErrors()) {
			return "home/usereditor";
		}

		JavaCode javaCode = new JavaCode();
		String saida = javaCode.executarCodigo(codeEditorDto.getEntrada());

		codeEditorDto.setNomeArquivo(javaCode.getFileName());
		codeEditorDto.setSaida(saida);
		
		System.out.println(codeEditorDto.getSaida());

//		CodeEditor codeEditor = codeEditorDto.toCodeEditor();
//		codeEditorRepository.save(codeEditor);

		return "home/usereditor";
	}

//	@RequestMapping(value="/edit", method=RequestMethod.POST, params="action=save")
//	public ModelAndView save() {
//		
//		
//	}

}
