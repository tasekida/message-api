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

public class LineWebhookBeacon {
    //// Fields
    /**
     *  検出されたビーコンのハードウェアID
     */
    private String hwid;
    /**
     *  ビーコンイベントのタイプ
     */
    private String type;
    /**
     *  検出されたビーコンのデバイスメッセージ
     */
    private String dm;

    //// Getters
    public String getHwid() {
        return this.hwid;
    }
    public String getType() {
        return this.type;
    }
    public String getDm() {
        return this.dm;
    }

    //// Setters
    public void setHwid(String _hwid) {
        this.hwid = _hwid;
    }
    public void setType(String _type) {
        this.type = _type;
    }
    public void setDm(String _dm) {
        this.dm = _dm;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Beacon [hwid=").append(hwid)
                .append(", type=").append(type)
                .append(", dm=").append(dm)
                .append("]");
        return builder.toString();
    }
}
