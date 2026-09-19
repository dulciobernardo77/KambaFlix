package com.KambaFlix.Service;

import com.KambaFlix.Entity.Streaming;
import com.KambaFlix.Repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StreamingService {
    private StreamingRepository repository;

    public StreamingService(StreamingRepository repository) {
        this.repository = repository;
    }

    public List<Streaming> findAll(){
        return repository.findAll();
    }

    public  Streaming SavedCategory (Streaming streaming){
            return repository.save(streaming);
    }
  public Streaming findById(Long id){
      Optional<Streaming> streaming = repository.findById(id);
      return streaming.orElse(null);
  }
  public void deleteByCategoryId (Long id){
        repository.deleteById(id);
  }
}
