package model.listUser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListUsersDto {
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    private List<UserDto> data;
    private SupportDto support;
    @JsonProperty("_meta")
    private MetaDto meta;
}
