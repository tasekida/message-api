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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LineWebhookPostbackParams {
    //// Fields
    /**
     *  ユーザーが選択した日付
     */
    private LocalDate date;
    /**
     *  ユーザーが選択した時刻
     */
    private LocalTime time;
    /**
     *  ユーザーが選択した日付と時刻
     */
    private LocalDateTime datetime;

    //// Getters
    public LocalDate getDate() {
        return this.date;
    }
    public LocalTime getTime() {
        return this.time;
    }
    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    //// Setters
    public void setDate(LocalDate _date) {
        this.date = _date;
    }
    public void setTime(LocalTime _time) {
        this.time = _time;
    }
    public void setDatetime(LocalDateTime _datetime) {
        this.datetime = _datetime;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("PostbackParams [date=").append(date)
                .append(", time=").append(time)
                .append(", datetime=").append(datetime).append("]");
        return builder.toString();
    }
}
