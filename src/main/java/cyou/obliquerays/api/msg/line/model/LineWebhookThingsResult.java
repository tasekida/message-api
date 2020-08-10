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

public class LineWebhookThingsResult {
    //// Fields
    /**
     *  実行されたシナリオID
     */
    private String scenarioId;
    /**
     *  実行したシナリオを含むシナリオセットのリビジョン
     */
    private int revision;
    /**
     *  シナリオのアクションの実行が開始された時刻（ミリ秒。LINEアプリの時刻）
     */
    private long startTime;
    /**
     *  シナリオの実行が完了した時刻（ミリ秒。LINEアプリの時刻）
     */
    private long endTime;
    /**
     *  シナリオの実行完了ステータス
     */
    private String resultCode;
    /**
     *  個々のアクションコマンドの実行結果
     */
    private final List<LineWebhookThingsActionResult> actionResults = new ArrayList<>(0);
    /**
     *  Notificationで受け取ったデータ
     */
    private String bleNotificationPayload;
    /**
     *  エラー理由
     */
    private String errorReason;

    //// Getters
    public String getScenarioId() {
        return this.scenarioId;
    }
    public int getRevision() {
        return this.revision;
    }
    public long getStartTime() {
        return this.startTime;
    }
    public long getEndTime() {
        return this.endTime;
    }
    public String getResultCode() {
        return this.resultCode;
    }
    public List<LineWebhookThingsActionResult> getActionResults() {
        return this.actionResults;
    }
    public String getBleNotificationPayload() {
        return this.bleNotificationPayload;
    }
    public String getErrorReason() {
        return this.errorReason;
    }

    //// Setters
    public void setScenarioId(String _scenarioId) {
        this.scenarioId = _scenarioId;
    }
    public void setRevision(int _revision) {
        this.revision = _revision;
    }
    public void setStartTime(long _startTime) {
        this.startTime = _startTime;
    }
    public void setEndTime(long _endTime) {
        this.endTime = _endTime;
    }
    public void setResultCode(String _resultCode) {
        this.resultCode = _resultCode;
    }
    public void setActionResults(List<LineWebhookThingsActionResult> _actionResults) {
        this.actionResults.clear();
        if (!_actionResults.isEmpty()) {
            this.actionResults.addAll(_actionResults);
        }
    }
    public void setBleNotificationPayload(String _bleNotificationPayload) {
        this.bleNotificationPayload = _bleNotificationPayload;
    }
    public void setErrorReason(String _errorReason) {
        this.errorReason = _errorReason;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ThingsResult [scenarioId=").append(scenarioId)
                .append(", revision=").append(revision)
                .append(", startTime=").append(startTime)
                .append(", endTime=").append(endTime)
                .append(", resultCode=").append(resultCode)
                .append(", actionResults=").append(actionResults)
                .append(", bleNotificationPayload=").append(bleNotificationPayload)
                .append(", errorReason=").append(errorReason).append("]");
        return builder.toString();
    }
}