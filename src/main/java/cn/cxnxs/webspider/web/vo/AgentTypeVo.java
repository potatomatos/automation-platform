package cn.cxnxs.webspider.web.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author mengjinyuan
 * @date 2020-11-22 01:32
 **/
public class AgentTypeVo {

    private Integer id;

    /**
     * agent类型名称
     */
    private String agentTypeName;

    /**
     * 处理器路径
     */
    private String handler;

    /**
     * 是否定时执行
     */
    private Boolean canBeScheduled;

    /**
     * 是否生成事件
     */
    private Boolean canCreateEvents;

    /**
     * 是否空运行
     */
    private Boolean canDryRun;

    /**
     * 是否接收事件
     */
    private Boolean canReceiveEvents;

    /**
     * 默认定时周期
     */
    private Integer defaultSchedule;

    private String defaultScheduleStr;

    private JSONArray schedules;

    /**
     * 使用方法
     */
    private String descriptionHtml;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 表单地址
     */
    private String formUrl;

    /**
     * 配置模板
     */
    private JSONObject optionsTemplate;

    /**
     * 表单校验规则，如果配置是json类型
     */
    private JSONObject optionsSchema;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    private JSONArray keepEventsTimes;
    /**
     * 执行计划枚举
     */
    public enum ScheduleEnum {
        EVERY_1M(1, "", "每1分钟"),
        EVERY_2M(2, "", "每2分钟"),
        EVERY_5M(3, "", "每5分钟"),
        EVERY_10M(4, "", "每10分钟"),
        EVERY_30M(5, "", "每30分钟"),
        EVERY_1H(6, "", "每1小时"),
        EVERY_2H(7, "", "每2小时"),
        EVERY_5H(8, "", "每5小时"),
        EVERY_12H(9, "", "每12小时"),
        EVERY_1D(10, "", "每天"),
        EVERY_2D(11, "", "每2天"),
        EVERY_MONDAY(12, "", "每周一"),
        MIDNIGHT(13, "", "每天零点"),
        AM1(14, "", "上午1点"),
        AM2(15, "", "上午2点"),
        AM3(16, "", "上午3点"),
        AM4(17, "", "上午4点"),
        AM5(18, "", "上午5点"),
        AM6(19, "", "上午6点"),
        AM7(20, "", "上午7点"),
        AM8(21, "", "上午8点"),
        AM9(22, "", "上午9点"),
        AM10(23, "", "上午10点"),
        AM11(24, "", "上午11点"),
        NOON(25, "", "中午12点"),
        PM1(26, "", "下午1点"),
        PM2(27, "", "下午2点"),
        PM3(28, "", "下午3点"),
        PM4(29, "", "下午4点"),
        PM5(30, "", "下午5点"),
        PM6(31, "", "下午6点"),
        PM7(32, "", "下午7点"),
        PM8(33, "", "晚上8点"),
        PM9(34, "", "晚上9点"),
        PM10(35, "", "晚上10点"),
        PM11(36, "", "晚上11点"),
        NEVER(37, "", "从不"),

        ;
        /**
         * 代码
         */
        private Integer code;

        /**
         * cron表达式
         */
        private String cron;

        /**
         * 描述
         */
        private String desc;

        ScheduleEnum(Integer code, String cron, String desc) {
            this.code = code;
            this.cron = cron;
            this.desc = desc;
        }


        /**
         * 将该枚举全部转化成json
         *
         * @return json字符串
         */
        public static JSONArray toJson() {
            JSONArray jsonArray = new JSONArray();
            for (ScheduleEnum e : ScheduleEnum.values()) {
                JSONObject object = new JSONObject();
                object.put("code", e.getCode());
                object.put("cron", e.getCron());
                object.put("desc", e.getDesc());
                jsonArray.add(object);
            }
            return jsonArray;
        }

        @Override
        public String toString() {
            JSONObject object = new JSONObject();
            object.put("code", code);
            object.put("cron", cron);
            object.put("desc", desc);
            return object.toJSONString();
        }

        public Integer getCode() {
            return code;
        }

        public String getCron() {
            return cron;
        }

        public static String getCron(Integer code) {
            for (ScheduleEnum e : ScheduleEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e.cron;
                }
            }
            return null;
        }

        public String getDesc() {
            return desc;
        }

        public static String getDesc(Integer code) {
            for (ScheduleEnum e : ScheduleEnum.values()) {
                if (e.getCode().equals(code)) {
                    return e.desc;
                }
            }
            return null;
        }
    }

    public enum KeepEventsTime{
        FOREVER(1, "", "永久"),
        ONR_HOUR(2, "", "1小时"),
        SIX_HOUR(3, "", "6小时"),
        ONE_DAY(4, "", "1天"),
        TWO_DAYS(5, "", "2天"),
        THREE_DAYS(6, "", "3天"),
        FOUR_DAYS(7, "", "4天"),
        FIVE_DAYS(8, "", "5天"),
        SIX_DAYS(9, "", "6天"),
        SEVEN_DAYS(10, "", "7天"),
        FOURTEEN_DAYS(11, "", "14天"),
        TWENTY_ONE_DAYS(12, "", "21天"),
        THIRTY_DAYS(13, "", "30天"),
        FORTY_FIVE_DAYS(14, "", "45天"),
        NINETY_DAYS(15, "", "90天"),
        ONE_HUNDRED_AND_EIGHTY_DAYS(16, "", "180天"),
        ONE_YEAR(17, "", "1年"),
        ;
        private Integer code;

        private String cron;

        private String desc;

        KeepEventsTime(Integer code, String cron, String desc) {
            this.code = code;
            this.cron = cron;
            this.desc = desc;
        }
        /**
         * 将该枚举全部转化成json
         *
         * @return json字符串
         */
        public static JSONArray toJson() {
            JSONArray jsonArray = new JSONArray();
            for (AgentTypeVo.KeepEventsTime e : AgentTypeVo.KeepEventsTime.values()) {
                JSONObject object = new JSONObject();
                object.put("code", e.getCode());
                object.put("cron", e.getCron());
                object.put("desc", e.getDesc());
                jsonArray.add(object);
            }
            return jsonArray;
        }
        public static String getCron(Integer code) {
            for (AgentTypeVo.KeepEventsTime e : AgentTypeVo.KeepEventsTime.values()) {
                if (e.getCode().equals(code)) {
                    return e.cron;
                }
            }
            return null;
        }
        public static String getDesc(Integer code) {
            for (AgentTypeVo.KeepEventsTime e : AgentTypeVo.KeepEventsTime.values()) {
                if (e.getCode().equals(code)) {
                    return e.desc;
                }
            }
            return null;
        }
        public Integer getCode() {
            return code;
        }

        public String getCron() {
            return cron;
        }

        public String getDesc() {
            return desc;
        }
    }

    public JSONArray getKeepEventsTimes() {
        this.keepEventsTimes = AgentTypeVo.KeepEventsTime.toJson();
        return keepEventsTimes;
    }
    public String getDefaultScheduleStr() {
        this.defaultScheduleStr = ScheduleEnum.getDesc(this.defaultSchedule);
        return this.defaultScheduleStr;
    }

    public JSONArray getSchedules() {
        this.schedules = ScheduleEnum.toJson();
        return schedules;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentTypeName() {
        return agentTypeName;
    }

    public void setAgentTypeName(String agentTypeName) {
        this.agentTypeName = agentTypeName;
    }

    public Boolean getCanBeScheduled() {
        return canBeScheduled;
    }

    public void setCanBeScheduled(Boolean canBeScheduled) {
        this.canBeScheduled = canBeScheduled;
    }

    public Boolean getCanCreateEvents() {
        return canCreateEvents;
    }

    public void setCanCreateEvents(Boolean canCreateEvents) {
        this.canCreateEvents = canCreateEvents;
    }

    public Boolean getCanDryRun() {
        return canDryRun;
    }

    public void setCanDryRun(Boolean canDryRun) {
        this.canDryRun = canDryRun;
    }

    public Boolean getCanReceiveEvents() {
        return canReceiveEvents;
    }

    public void setCanReceiveEvents(Boolean canReceiveEvents) {
        this.canReceiveEvents = canReceiveEvents;
    }

    public Integer getDefaultSchedule() {
        return defaultSchedule;
    }

    public void setDefaultSchedule(Integer defaultSchedule) {
        this.defaultSchedule = defaultSchedule;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFormUrl() {
        return formUrl;
    }

    public void setFormUrl(String formUrl) {
        this.formUrl = formUrl;
    }

    public JSONObject getOptionsTemplate() {
        return optionsTemplate;
    }

    public void setOptionsTemplate(String optionsTemplate) {
        if (optionsTemplate != null) {
            this.optionsTemplate = JSONObject.parseObject(optionsTemplate);
        }
    }

    public JSONObject getOptionsSchema() {
        return optionsSchema;
    }

    public void setOptionsSchema(String optionsSchema) {
        if (optionsSchema != null) {
            this.optionsSchema = JSONObject.parseObject(optionsSchema);
        }
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "AgentTypeVo{" +
                "id=" + id +
                ", agentTypeName='" + agentTypeName + '\'' +
                ", canBeScheduled=" + canBeScheduled +
                ", canCreateEvents=" + canCreateEvents +
                ", canDryRun=" + canDryRun +
                ", canReceiveEvents=" + canReceiveEvents +
                ", defaultSchedule='" + defaultSchedule + '\'' +
                ", descriptionHtml='" + descriptionHtml + '\'' +
                ", introduction='" + introduction + '\'' +
                ", formUrl='" + formUrl + '\'' +
                ", optionsTemplate=" + optionsTemplate +
                ", optionsSchema=" + optionsSchema +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
