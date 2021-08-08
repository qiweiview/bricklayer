package cn.bricklayer.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NDM2Server {
    private String name;

    private List<NDM2Schema> schemas;

    public List<NDM2Table> getAllTables() {
        List<NDM2Table> collect = schemas.stream().flatMap(x -> {
            return x.getTables().stream();
        }).collect(Collectors.toList());
        return collect;

    }
}
