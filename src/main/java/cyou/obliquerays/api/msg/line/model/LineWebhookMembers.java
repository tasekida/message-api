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

import java.util.ArrayList;
import java.util.List;

public class LineWebhookMembers {
    //// Fields
    /**
     *  退出したユーザーオブジェクトの配列
     */
    private final List<LineWebhookSource> members = new ArrayList<>(0);

    //// Getters
    public List<LineWebhookSource> getMembers() {
        return this.members;
    }

    //// Setters
    public void setMembers(List<LineWebhookSource> _members) {
        this.members.clear();
        if (!_members.isEmpty()) {
            this.members.addAll(_members);
        }
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Left [members=").append(members).append("]");
        return builder.toString();
    }
}
