package smartcity.kni.wirtualnaapteczka.regularConfigurator;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "regularConfig"
})
public class RegularConfigJSON {

    @JsonProperty("regularConfig")
    private List<Object> regularConfig = new ArrayList<Object>();

    @JsonProperty("regularConfig")
    public List<Object> getRegularConfig() {
        return regularConfig;
    }

    @JsonProperty("regularConfig")
    public void setRegularConfig(List<Object> regularConfig) {
        this.regularConfig = regularConfig;
    }

}