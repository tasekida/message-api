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

public class LineWebhookContentProvider {
    //// Fields
    /**
     *  画像ファイルの提供元
     */
    private String type;
    /**
     *  画像ファイルのURL
     */
    private String originalContentUrl;
    /**
     *  プレビュー画像のURL
     */
    private String previewImageUrl;

    //// Getters
    public String getType() {
        return this.type;
    }
    public String getOriginalContentUrl() {
        return this.originalContentUrl;
    }
    public String getPreviewImageUrl() {
        return this.previewImageUrl;
    }

    //// Setters
    public void setType(String _type) {
        this.type = _type;
    }
    public void setOriginalContentUrl(String _originalContentUrl) {
        this.originalContentUrl = _originalContentUrl;
    }
    public void setPreviewImageUrl(String _previewImageUrl) {
        this.previewImageUrl = _previewImageUrl;
    }

    //// Methods
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ContentProvider [type=").append(type)
                .append(", originalContentUrl=").append(originalContentUrl)
                .append(", previewImageUrl=").append(previewImageUrl).append("]");
        return builder.toString();
    }
}