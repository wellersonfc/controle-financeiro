package br.com.wellerson.financecontrol.service;

import br.com.wellerson.financecontrol.dto.UsuarioRequest;
import br.com.wellerson.financecontrol.dto.UsuarioResponse;
import br.com.wellerson.financecontrol.entity.Usuario;
import br.com.wellerson.financecontrol.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Cria usuário com senha criptografada
    @Transactional
    public UsuarioResponse criar(UsuarioRequest request) {

        String email = request.email().trim().toLowerCase();

        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setDataNascimento(request.dataNascimento());
        usuario.setTelefone(request.telefone());
        usuario.setStatus(Usuario.Status.ATIVO);
        usuario.setPerfil(Usuario.Perfil.USUARIO);

        usuario = usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

    // Lista todos os usuários
    @Transactional(readOnly = true)
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Busca usuário por ID
    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
        return toResponse(usuario);
    }

    // Remove usuário do banco
    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

    // Converte entidade para DTO
    private UsuarioResponse toResponse(Usuario u) {
        return new UsuarioResponse(
                u.getId(),
                u.getNome(),
                u.getEmail(),
                u.getDataNascimento(),
                u.getTelefone(),
                u.getStatus().name(),
                u.getPerfil().name(),
                u.getCriadoEm()
        );
    }
}