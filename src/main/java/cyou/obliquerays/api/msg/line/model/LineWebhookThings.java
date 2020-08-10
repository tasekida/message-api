/**
 *  Copyright 2020 tasekida
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cyou.obliquerays.api.msg.line.model;

public class LineWebhookThings {
    //// Fields
    /**
     *  LINE連携デバイスのデバイスIDです
     */
    private String deviceId;
    /**
     *  LINE連携イベントの識別子です
     */
    private String type;
    /**
     *  LINE連携イベントの識別子です
     */
    private LineWebhookThingsResult result;

    //// Getters
    public String getDeviceId() {
        return this.deviceId;
    }
    public String getType() {
        return this.type;
    }
    public LineWebhookThingsResult getResult() {
        return this.result;
    }

    //// Setters
    public void setDeviceId(String _deviceId) {
        this.deviceId = _deviceId;
    }
    public void setType(String _type) {
        this.type = _type;
    }
    public void setResult(LineWebhookThingsResult _result) {
        this.result = _result;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Things [deviceId=").append(deviceId)
                .append(", type=").append(type)
                .append(", result=").append(result).append("]");
        return builder.toString();
    }
}