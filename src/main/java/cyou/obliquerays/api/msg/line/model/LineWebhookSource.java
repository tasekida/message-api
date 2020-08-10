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

public class LineWebhookSource {
    //// Fields
    /**
     *  イベント情報のタイプを表す識別子
     */
    private String type;
    /**
     *  送信元ユーザー
     */
    private String userId;
    /**
     *  送信元グループのID
     */
    private String groupId;
    /**
     *  送信元トークルームのID
     */
    private String roomId;

    //// Getters
    public String getType() {
        return this.type;
    }
    public String getUserId() {
        return this.userId;
    }
    public String getGroupId() {
        return this.groupId;
    }
    public String getRoomId() {
        return this.roomId;
    }

    //// Setters
    public void setType(String _type) {
        this.type = _type;
    }
    public void setUserId(String _userId) {
        this.userId = _userId;
    }
    public void setGroupId(String _groupId) {
        this.groupId = _groupId;
    }
    public void setRoomId(String _roomId) {
        this.roomId = _roomId;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Source [type=").append(type).append(", userId=").append(userId)
                .append(", groupId=").append(groupId).append(", roomId=").append(roomId).append("]");
        return builder.toString();
    }
}
