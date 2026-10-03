package com.KambaFlix.Controller;

import com.KambaFlix.Controller.request.StreamingRequest;
import com.KambaFlix.Controller.response.StreamingResponse;
import com.KambaFlix.Entity.Streaming;
import com.KambaFlix.Service.StreamingService;
import com.KambaFlix.mapper.StreamingMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kambaflix/streaming")
@RequiredArgsConstructor
@Tag(name = "Serviços de streaming", description = "Gestão dos serviços de streaming associados aos filmes.")
@SecurityRequirement(name = "bearerAuth")
public class StreamingController {

    private final StreamingService streamingService;
    

    @GetMapping()
    @Operation(summary = "Listar serviços de streaming", description = "Devolve todos os serviços de streaming disponíveis.")
    @ApiResponse(responseCode = "200", description = "Lista de serviços devolvida com sucesso.")
    public ResponseEntity<List<StreamingResponse>> getAllCategory(){
        List<StreamingResponse> streamings= streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streamings);
    }
    @PostMapping("cadastrar")
    @Operation(summary = "Criar serviço de streaming", description = "Adiciona um novo serviço de streaming.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Serviço de streaming criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Dados inválidos; o nome é obrigatório.")
    })
    public ResponseEntity<StreamingResponse>  SavedCategory(@Valid @RequestBody StreamingRequest request){
        Streaming SavedStreaming = streamingService.SavedCategory(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(SavedStreaming));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obter serviço de streaming", description = "Procura um serviço de streaming pelo seu identificador.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Serviço de streaming encontrado."),
            @ApiResponse(responseCode = "404", description = "Não existe um serviço com o identificador indicado.")
    })
    public  ResponseEntity<?> getByCategoryId(@PathVariable Long id){
        if (streamingService.findById(id) != null){
            Streaming streaming = streamingService.findById(id);
            return ResponseEntity.ok(streaming);
        }else{
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A Streaming com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover serviço de streaming", description = "Remove um serviço de streaming.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Serviço removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Não existe um serviço com o identificador indicado.")
    })
    public  ResponseEntity<String> deleteByCategoryId(@PathVariable Long id){
      if (streamingService.findById(id) != null) {
          streamingService.deleteByCategoryId(id);
          return ResponseEntity.ok("Categoria com IDs: "+id+" excluido com sucesso");
      }else {
          return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A categoria com IDs: "+id+" nao encontrado nos nossos banco de dados");
      }
    }
}
