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
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DelayedJobs extends Model<DelayedJobs> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务权重
     */
    private Integer priority;

    /**
     * 失败尝试次数
     */
    private Integer attempts;

    /**
     * 处理器
     */
    private String handler;

    /**
     * 错误信息
     */
    private String lastError;

    /**
     * 运行时间
     */
    private LocalDateTime runAt;

    /**
     * 线程id
     */
    private Integer threadId;

    /**
     * 发生错误时间
     */
    private LocalDateTime failedAt;

    /**
     * 所在队列
     */
    private String queue;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
