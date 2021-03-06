/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.orchestration.core.configcenter.listener;

import java.util.Collections;
import org.apache.shardingsphere.infra.yaml.engine.YamlEngine;
import org.apache.shardingsphere.metrics.configuration.swapper.MetricsConfigurationYamlSwapper;
import org.apache.shardingsphere.metrics.configuration.yaml.YamlMetricsConfiguration;
import org.apache.shardingsphere.orchestration.center.ConfigCenterRepository;
import org.apache.shardingsphere.orchestration.center.listener.DataChangedEvent;
import org.apache.shardingsphere.orchestration.core.common.event.MetricsConfigurationChangedEvent;
import org.apache.shardingsphere.orchestration.core.common.listener.PostShardingCenterRepositoryEventListener;
import org.apache.shardingsphere.orchestration.core.configcenter.ConfigCenterNode;

/**
 * Metrics configuration changed listener.
 */
public final class MetricsConfigurationChangedListener extends PostShardingCenterRepositoryEventListener {
    
    public MetricsConfigurationChangedListener(final String name, final ConfigCenterRepository configCenterRepository) {
        super(configCenterRepository, Collections.singletonList(new ConfigCenterNode(name).getMetricsPath()));
    }
    
    @Override
    protected MetricsConfigurationChangedEvent createShardingOrchestrationEvent(final DataChangedEvent event) {
        return new MetricsConfigurationChangedEvent(new MetricsConfigurationYamlSwapper().swap(YamlEngine.unmarshal(event.getValue(), YamlMetricsConfiguration.class)));
    }
}
