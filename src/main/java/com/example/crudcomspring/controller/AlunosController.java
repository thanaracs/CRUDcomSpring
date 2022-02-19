package com.example.crudcomspring.controller;

import com.example.crudcomspring.dao.AlunoDAO;
import com.example.crudcomspring.model.Aluno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("alunos")
public class AlunosController {
    AlunoDAO dao;

    public AlunosController() {
        dao = new AlunoDAO();
    }


    @GetMapping("/form")
    public String form(Aluno aluno) {
        return "/alunos/form";
    }

    @GetMapping("/list")
    public ModelAndView listar(ModelMap model) {
        model.addAttribute("alunos", dao.buscarAlunos());
        return new ModelAndView("/alunos/list", model);
    }

    @PostMapping("/save")
    public ModelAndView save(Aluno aluno){
        dao.save(aluno);
        return new ModelAndView("redirect:/alunos/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        dao.remove(id);
        return new ModelAndView("redirect:/alunos/list");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("aluno", dao.buscarAluno(id));
        return new ModelAndView("/alunos/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Aluno aluno) {
        dao.update(aluno);
        return new ModelAndView("redirect:/alunos/list");
    }


}
