package br.com.wellerson.financecontrol.service;
import br.com.wellerson.financecontrol.dto.CategoriaDespesaRequest;
import br.com.wellerson.financecontrol.dto.CategoriaDespesaResponse;
import br.com.wellerson.financecontrol.entity.CategoriaDespesa;
import br.com.wellerson.financecontrol.entity.Despesa;
import br.com.wellerson.financecontrol.repository.CategoriaDespesaRepository;

import org.springframework.stereotype.Service;

@Service
public class CategoriaDespesaService {

    final CategoriaDespesaRepository categoriaDespesaRepository;    

    public CategoriaDespesaService(CategoriaDespesaRepository categoriaDespesaRepository) {
        this.categoriaDespesaRepository = categoriaDespesaRepository;
    }

    public CategoriaDespesaResponse criarCategoriaDespesa(CategoriaDespesaRequest request) {
        
        CategoriaDespesa categoriaDespesa = new CategoriaDespesa();
        categoriaDespesa.setNome(request.nome());
        categoriaDespesa.setDescricao(request.descricao());
        categoriaDespesa.setCreatedAt(java.time.LocalDate.now());

        CategoriaDespesa novaCategoria = categoriaDespesaRepository.save(categoriaDespesa);
        return toResponse(novaCategoria);
    }

    public CategoriaDespesaResponse atualizarCategoriaDespesa(Long id, CategoriaDespesaRequest request) {
        CategoriaDespesa categoriaDespesa = categoriaDespesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria de despesa não encontrada"));

        categoriaDespesa.setNome(request.nome());
        categoriaDespesa.setDescricao(request.descricao());

        CategoriaDespesa categoriaAtualizada = categoriaDespesaRepository.save(categoriaDespesa);
        return toResponse(categoriaAtualizada);
    }   

    public void excluirCategoriaDespesa(Long id) {
        CategoriaDespesa categoriaDespesa = categoriaDespesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria de despesa não encontrada"));

        categoriaDespesaRepository.delete(categoriaDespesa);
    }

    public CategoriaDespesaResponse obterCategoriaDespesaPorId(Long id) {
        CategoriaDespesa categoriaDespesa = categoriaDespesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria de despesa não encontrada"));

        return toResponse(categoriaDespesa);
    }

    public java.util.List<CategoriaDespesaResponse> listarCategoriasDespesas() {
        java.util.List<CategoriaDespesa> categorias = categoriaDespesaRepository.findAll();
        return categorias.stream()
                .map(this::toResponse)
                .toList();
    }

    private CategoriaDespesaResponse toResponse(CategoriaDespesa categoriaDespesa) {
        return new CategoriaDespesaResponse(
                categoriaDespesa.getId(),
                categoriaDespesa.getNome(),
                categoriaDespesa.getDescricao(),
                categoriaDespesa.getCreatedAt().toString()
        );
    }   
    
}
