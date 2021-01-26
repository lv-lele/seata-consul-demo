package com.yonyou.cloud.common.worker;

/**
 * 雪花算法工作者
 */
public class SnowFlakeIdWorker {
    /** 开始时间戳(2020-01-01) */
    private final long poch = 1577808000000L;

    /** 机器ID所占的位数 */
    private final long workerIdBits = 5L;

    /** 数据标识ID所占的位数 */
    private final long dataCenterIdBits = 5L;

    /** 支持最大的机器ID, 结果是31,  */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /** 支持最大的数据标识ID */
    private final long maxDataCenterId = ~(-1L << dataCenterIdBits);

    /** 序列在ID中所占的位数 */
    private final long sequenceBits = 12L;

    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;

    /** 数据标识ID向左移17位 */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    /** 时间戳向左移22位 */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /** 生成序列的编码 */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /** 工作机器ID(0-31) */
    private long workerId;

    /** 数据中心ID(0-31) */
    private long dataCenterId;

    /** 毫秒内序列(0-4095) */
    private long sequence = 0L;

    /** 上次生成ID的时间戳 */
    private long lastTimestamp = -1L;

    /**
     * 不允许孔构造
     */
    private SnowFlakeIdWorker(){}

    /**
     * 构造函数
     * @param workerId 工作ID(0-31)
     * @param dataCenterId 数据中心ID(0-31)
     */
    public SnowFlakeIdWorker(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {//工作ID参数异常
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }

        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {//数据中心ID参数异常
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }

        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获取下一个ID(线程安全)
     * @return SnowFlakeId
     */
    public synchronized long nextId() {
        long currentTimestamp = this.timeGen();//毫秒级当前时间

        //如果当前时间小于上一次ID生成的时间, 说明系统的时钟回退过, 此时应当抛出异常
        if (currentTimestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - currentTimestamp));
        }

        //如果是同一时间生成的, 则进行毫秒内序列
        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (0 == sequence) {//毫秒内序列溢出
                //阻塞到下一毫秒, 获得新的时间戳
                currentTimestamp = this.tilNextMillis(lastTimestamp);
            }
        } else {//时间戳改变, 毫秒内序列重置
            sequence = 0L;
        }

        lastTimestamp = currentTimestamp;//上次生成的ID时间戳

        //移位并通过或运算拼到一起组成64位的ID
        return ((currentTimestamp - poch) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
    }

    /**
     * 阻塞到下一毫秒, 直到获得新的时间戳
     * @param lastTimestamp 上次生成的ID的时间戳
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long currentTimestamp = this.timeGen();//当前时间戳

        while (currentTimestamp <= lastTimestamp) {
            currentTimestamp = this.timeGen();
        }

        return currentTimestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }
}
