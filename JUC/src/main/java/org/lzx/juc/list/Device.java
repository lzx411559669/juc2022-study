package org.lzx.juc.list;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liuzhengxing
 * @version v1.0
 * @package org.lzx.juc.list
 * @data 2023/11/20 22:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class Device {
    private String ip;

    private boolean isUseing;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isUseing() {
        return isUseing;
    }

    public void setUseing(boolean useing) {
        isUseing = useing;
    }
}
