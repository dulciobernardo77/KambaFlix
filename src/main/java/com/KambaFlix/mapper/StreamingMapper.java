package com.KambaFlix.mapper;

import com.KambaFlix.Controller.request.StreamingRequest;
import com.KambaFlix.Controller.response.StreamingResponse;
import com.KambaFlix.Entity.Streaming;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamingMapper {

    public  static Streaming toStreaming(StreamingRequest request){
        return Streaming
                .builder()
                .nome(request.nome())
                .build();
    }

    public  static StreamingResponse toStreamingResponse(Streaming streaming){
        return StreamingResponse
                .builder()
                .id(streaming.getId())
                .nome(streaming.getNome())
                .build();
    }
}
