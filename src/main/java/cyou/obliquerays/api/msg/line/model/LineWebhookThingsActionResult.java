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

public class LineWebhookThingsActionResult {
    //// Fields
    /**
     *  実行されたアクションのtype
     */
    private String type;
    /**
     *  Base64形式でエンコードされたバイナリデータ
     */
    private String data;

    //// Getters
    public String getType() {
        return this.type;
    }
    public String getData() {
        return this.data;
    }

    //// Setters
    public void setType(String _type) {
        this.type = _type;
    }
    public void setData(String _data) {
        this.data = _data;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ThingsActionResult [type=").append(type)
                .append(", data=").append(data).append("]");
        return builder.toString();
    }
}