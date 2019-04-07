package com.ambarish.logs.logprocessor.model.response;


import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class StatusResponse {

    private Map<String,String> oStatus;

    @Override
    public String toString() {
        if( oStatus == null || oStatus.size() == 0){
            return "{} # Object Didn't Exist at that time.";
        }

        List<String> keys = Lists.newArrayList(oStatus.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\""+keys.get(0)+"\"=>\""+oStatus.get(keys.get(0))+"\"");
        for(int i=1;i<keys.size();i++){
            sb.append(", \""+keys.get(i)+"\"=>\""+oStatus.get(keys.get(i))+"\"");
        }
        sb.append("}");

        return sb.toString();
    }
}
