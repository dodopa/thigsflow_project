package com.dodopa.thingsflowproject

object Util {
    private val TAG = "DODOPA_LOG"

    object Log {
        /**
         * Print warning log
         * */
        fun w(msg: String?, tag: String = TAG) {
            if (BuildConfig.DEBUG) {
                android.util.Log.w(tag, msg)
            }
        }

        /**
         * Print error log
         * */
        fun e(msg: String?, tag: String = TAG) {
            if (BuildConfig.DEBUG) {
                android.util.Log.e(tag, msg)
            }
        }

        /**
         * Print debug log
         * */
        fun d(msg: String?, tag: String = TAG) {
            if (BuildConfig.DEBUG) {
                android.util.Log.d(tag, msg)
            }
        }

        /**
         * Print info log
         * */
        fun i(msg: String?, tag: String = TAG) {
            if (BuildConfig.DEBUG) {
                android.util.Log.i(tag, msg)
            }
        }

        /**
         * Print verbose info
         * */
        fun v(msg: String?, tag: String = TAG) {
            if (BuildConfig.DEBUG) {
                android.util.Log.v(tag, msg)
            }
        }
    }
}