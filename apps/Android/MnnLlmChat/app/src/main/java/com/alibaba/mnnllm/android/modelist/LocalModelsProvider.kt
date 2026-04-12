package com.alibaba.mnnllm.android.modelist

import android.util.Log
import com.alibaba.mls.api.ModelItem
import java.io.File

object LocalModelsProvider {
    /**
     * you can add ModelItem.fromLocalModel("Qwen-Omni-7B", "/data/local/tmp/omni_test/model")
     * to load local models
     */
    fun getLocalModels(): MutableList<ModelItem> {
        val result = mutableListOf<ModelItem>()
        try {
            val modelsDir = File("/sdcard/MNN_Models/")
            if (modelsDir.exists() && modelsDir.isDirectory) {
                modelsDir.listFiles()?.forEach { modelDir ->
                    if (modelDir.isDirectory && File(modelDir, "config.json").exists()) {
                        val modelPath = modelDir.absolutePath
                        val modelId = "local/${modelPath}"
                        result.add(ModelItem.fromLocalModel(modelId, modelPath))
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("LocalModelsProvider", "Failed to load models from /sdcard/MNN_Models/", e)
        }
        return result
    }
}
