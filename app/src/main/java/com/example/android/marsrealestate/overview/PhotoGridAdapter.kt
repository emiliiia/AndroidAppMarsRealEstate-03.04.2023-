/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsrealestate.databinding.GridViewItemBinding
import com.example.android.marsrealestate.network.MarsProperty


//розширює ListAdapter для відображення списку MarsProperty у вигляді сітки фотографій
class PhotoGridAdapter : ListAdapter<MarsProperty,
    PhotoGridAdapter.MarsPropertyViewHolder>(DiffCallback) {

        //створення нових елементів RecyclerView, тобто віджетів GridViewItemBinding,
        //коли RecyclerView потребує їх відображення. Функція повертає MarsPropertyViewHolder,
        //який містить прив’язку до GridViewItemBinding
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridAdapter.MarsPropertyViewHolder {
            return MarsPropertyViewHolder(GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)))
        }

         //отримує позицію елемента і встановлює значення відповідного об’єкта MarsProperty
        override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPropertyViewHolder, position: Int) {
            val marsProperty = getItem(position)
            holder.bind(marsProperty)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty,
                                     newItem: MarsProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty,
                                        newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }


    //містить прив’язку до GridViewItemBinding і функцію bind,
    // яка прив’язує MarsProperty до GridViewItemBinding та виконує відображення
    class MarsPropertyViewHolder(private var binding:
                                 GridViewItemBinding
    ):
        RecyclerView.ViewHolder(binding.root) {

        fun bind(marsProperty: MarsProperty) {
            binding.property = marsProperty
            binding.executePendingBindings()
        }
    }

}