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

/**
 * Line Messaging APIのメッセージObject
 */
public class LineMessagingApiMessage {
    //// Fields
    /**
     * メッセージ種別
     */
    private String type;
    /**
     * メッセージテキスト
     */
    private String text;

    //// Getters
    /**
     * @return メッセージ種別
     */
    public String getType() {
        return this.type;
    }
    /**
     * @return メッセージテキスト
     */
    public String getText() {
        return this.text;
    }

    //// Setters
    /**
     * @param _type メッセージ種別
     */
    public void setType(String _type) {
        this.type = _type;
    }
    /**
     * @param _text メッセージテキスト
     */
    public void setText(String _text) {
        this.text = _text;
    }
}
