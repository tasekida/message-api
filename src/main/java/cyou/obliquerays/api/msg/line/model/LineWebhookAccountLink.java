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

public class LineWebhookAccountLink {
    //// Fields
    /**
     *  連携が成功したかどうかを示す値
     */
    private String result;
    /**
     *  ユーザーIDの検証時に指定したnonce
     */
    private String nonce;

    //// Getters
    public String getResult() {
        return this.result;
    }
    public String getNonce() {
        return this.nonce;
    }

    //// Setters
    public void setResult(String _result) {
        this.result = _result;
    }
    public void setNonce(String _nonce) {
        this.nonce = _nonce;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AccountLink [result=").append(result)
                .append(", nonce=").append(nonce).append("]");
        return builder.toString();
    }
}