package cn.cxnxs.webspider.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author mengjinyuan
 * @since 2021-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AgentLogs extends Model<AgentLogs> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer agentId;

    private String message;

    private Integer level;

    private Integer inboundEventId;

    private Integer outboundEventId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
