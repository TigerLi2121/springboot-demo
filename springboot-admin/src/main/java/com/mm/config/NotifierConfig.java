package com.mm.config;

import com.mm.notifier.DingTalkNotifier;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通知配置
 *
 * @author lwl
 */
@Configuration
public class NotifierConfig {

    @Bean
    public DingTalkNotifier dingTalkNotifier(InstanceRepository repository) {
        return new DingTalkNotifier(repository);
    }
}
