# seata-consul-demo

docker 部署 seata-server (TC)

docker run --name seata-server \
            -p 8091:8091 \
            -v /root/seata/config:/root/seata-config \
            -e SEATA_CONFIG_NAME=file:/root/seata-config/registry \
            -e SEATA_IP=10.XXX.X.XXX \
            seataio/seata-server:1.3.0
