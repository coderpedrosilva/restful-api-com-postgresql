package com.projeto.projetojava.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.projeto.projetojava.model.Produto;
import com.projeto.projetojava.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository {

    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Método para retornar uma lista de produtos
     * 
     * @return Lista de produtos
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Método que retorna o produto encontrado pelo seu Id
     * 
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    /**
     * Método que adiciona um produto na lista de produtos
     * 
     * @param produto que será adicionado
     * @return Retorna o produto adicionado
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Método para deletar um produto da lista de produtos por Id
     * 
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Método que atualiza um produto na lista de produtos
     * 
     * @param produto que será atualizado
     * @return Retorna o produto atualizado
     */
    public Produto atualizar(Produto produto) {
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        if (produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não pode ser atualizado pois não existe");
        }
        deletar(produto.getId());

        produtos.add(produto);

        return produto;
    }
}
