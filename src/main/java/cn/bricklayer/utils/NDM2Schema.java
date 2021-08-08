package cn.bricklayer.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NDM2Schema {
    private String name;

    private List<NDM2Table> tables;
}
