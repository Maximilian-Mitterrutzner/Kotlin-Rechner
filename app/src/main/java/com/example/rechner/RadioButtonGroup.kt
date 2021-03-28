package com.example.rechner

import android.widget.RadioButton

/**
 * RadioGroups only work if the RadioButtons are direct children of the RadioGroup.
 * I wanted to use a view in between to apply custom layout to the RadioButtons,
 * so RadioGroups don't work here.
 * This is essentially just normal RadioGroup logic.
 */
class RadioButtonGroup(vararg buttons: RadioButton) {
    init {
        var guard = false
        for (button in buttons) {
            button.setOnCheckedChangeListener { buttonView, isChecked ->
                if(guard) {
                    return@setOnCheckedChangeListener;
                }

                guard = true;

                for (btn in buttons) {
                    if(btn != buttonView) {
                        btn.isChecked = false
                    }
                }

                guard = false;
            }
        }
    }
}