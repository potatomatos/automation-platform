package cn.cxnxs.webspider.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author mengjinyuan
 * @date 2020-11-25 14:16
 **/
@Data
public class UserVo implements Serializable {

    private Integer id;

    private String email;

    private LocalDateTime resetPasswordSentTime;

    private Integer loginCount;

    private LocalDateTime currentLoginTime;

    private LocalDateTime lastLoginTime;

    private String currentLoginIp;

    private String lastLoginIp;

    private Boolean admin;

    private Integer failedAttempts;

    private LocalDateTime lockedTime;

    private String username;

    private Integer serviceCount;

    private LocalDateTime confirmedTime;

    private LocalDateTime confirmationSentTime;

    private String unconfirmedEmail;

    private Boolean state;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String token;
}
