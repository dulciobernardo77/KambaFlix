package com.KambaFlix.Controller;

import com.KambaFlix.Controller.request.StreamingRequest;
import com.KambaFlix.Controller.response.StreamingResponse;
import com.KambaFlix.Entity.Streaming;
import com.KambaFlix.Service.StreamingService;
import com.KambaFlix.mapper.StreamingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kambaflix/streaming")
@RequiredArgsConstructor
public class StreamingController {

    private final StreamingService streamingService;
    

    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllCategory(){
        List<StreamingResponse> streamings= streamingService.findAll()
                .stream()
                .map(StreamingMapper::toStreamingResponse)
                .toList();
        return ResponseEntity.ok(streamings);
    }
    @PostMapping("cadastrar")
    public ResponseEntity<StreamingResponse>  SavedCategory(@RequestBody StreamingRequest request){
        Streaming SavedStreaming = streamingService.SavedCategory(StreamingMapper.toStreaming(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(StreamingMapper.toStreamingResponse(SavedStreaming));
    }

    @GetMapping("/{id}")
    public  ResponseEntity<?> getByCategoryId(@PathVariable Long id){
        if (streamingService.findById(id) != null){
            Streaming streaming = streamingService.findById(id);
            return ResponseEntity.ok(streaming);
        }else{
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A Streaming com IDs: "+id+" nao encontrado nos nossos banco de dados");
        }
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteByCategoryId(@PathVariable Long id){
      if (streamingService.findById(id) != null) {
          streamingService.deleteByCategoryId(id);
          return ResponseEntity.ok("Categoria com IDs: "+id+" excluido com sucesso");
      }else {
          return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("A categoria com IDs: "+id+" nao encontrado nos nossos banco de dados");
      }
    }
}
