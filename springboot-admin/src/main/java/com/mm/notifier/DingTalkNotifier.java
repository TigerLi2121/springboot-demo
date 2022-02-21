package com.mm.notifier;

import cn.hutool.json.JSONUtil;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * 钉钉通知
 *
 * @author lwl
 */
@Slf4j
public class DingTalkNotifier extends AbstractStatusChangeNotifier {


    public DingTalkNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        String serviceName = instance.getRegistration().getName();
        String serviceUrl = instance.getRegistration().getServiceUrl();
        String status = instance.getStatusInfo().getStatus();
        Map<String, Object> details = instance.getStatusInfo().getDetails();
        StringBuilder str = new StringBuilder();
        str.append("监控报警 : 【" + serviceName + "】");
        str.append("【服务地址】" + serviceUrl);
        str.append("【状态】" + status);
        str.append("【详情】" + JSONUtil.toJsonStr(details));
        return Mono.fromRunnable(() -> {
            log.info("{}", str);
        });
    }
}
