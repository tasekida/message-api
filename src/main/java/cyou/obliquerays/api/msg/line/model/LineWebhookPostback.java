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

public class LineWebhookPostback {
    //// Fields
    /**
     *  ポストバックデータ
     */
    private String data;
    /**
     *  日時選択アクションを介してユーザーが選択した日時を含むJSONオブジェクト
     */
    private LineWebhookPostbackParams params;

    //// Getters
    public String getData() {
        return this.data;
    }
    public LineWebhookPostbackParams getParams() {
        return this.params;
    }

    //// Setters
    public void setData(String _data) {
        this.data = _data;
    }
    public void setParams(LineWebhookPostbackParams _params) {
        this.params = _params;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Postback [data=").append(data)
                .append(", params=").append(params).append("]");
        return builder.toString();
    }
}