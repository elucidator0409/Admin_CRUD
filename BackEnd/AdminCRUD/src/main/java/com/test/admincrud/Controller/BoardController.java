package com.test.admincrud.Controller;

import com.test.admincrud.Entity.Board;
import com.test.admincrud.Entity.Client;
import com.test.admincrud.Exception.ResourceNotFoundException;
import com.test.admincrud.Repository.BoardRepository;
import com.test.admincrud.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    @GetMapping("/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody Board board) {
        Client author = clientRepository.findById(board.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Board not found"));

        board.setAuthor(author);
        Board createdBoard = boardRepository.save(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board updatedBoard) {
        Board existingBoard = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + id));



        existingBoard.setTitle(updatedBoard.getTitle());
        existingBoard.setDescription(updatedBoard.getDescription());
        Client author = clientRepository.findById(updatedBoard.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existingBoard.setAuthor(author);

        Board updated = boardRepository.save(existingBoard);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + id));

        boardRepository.delete(board);
        return ResponseEntity.ok().build();
    }

}
