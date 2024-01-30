package com.ashraf.hospital.ui.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.ashraf.hospital.ui.theme.ContentTernary
import com.ashraf.hospital.ui.theme.Primary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownTextField(
    options: List<String>,
    icon: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            shape = RoundedCornerShape(8.dp),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = ContentTernary
            ),
            modifier = modifier
                .fillMaxWidth()
                .menuAnchor(),
            leadingIcon = {
                Icon(
                    painter = icon,
                    contentDescription = contentDescription,
                    tint = Primary,
                    modifier = Modifier.size(24.dp),
                )
            },
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText = selectionOption
                        expanded = false
                        onClick(selectionOption)
                    },
                    text = {
                        Text(text = selectionOption)
                    },
                )
            }
        }
    }
}