package com.example.authapp.domain.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.authapp.R
import com.example.authapp.databinding.ItemRecyclerPaymentsBinding
import com.example.authapp.domain.models.PaymentModel
import com.example.authapp.ext.toDate
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PaymentsAdapter(
    private val items: List<PaymentModel>
): BaseAdapter<ItemRecyclerPaymentsBinding>(),KoinComponent {

    private val context: Context by inject()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemRecyclerPaymentsBinding> {
        _binding = ItemRecyclerPaymentsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BaseViewHolder(binding)    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemRecyclerPaymentsBinding>,
        position: Int
    ) {
        binding.apply {
            with(items[position]) {
                paymentTitle.text = title
                paymentAmount.text =
                    if(amount.isNullOrEmpty()) context.getString(R.string.amount_error)
                    else context.getString(R.string.payment_amount, amount)
                paymentId.text = context.getString(R.string.payment_id,id)
                paymentCreated.text =
                    if(created == null) context.getString(R.string.created_error)
                    else context.getString(R.string.payment_created, created.toString().toDate())

            }
        }
    }

    override fun getItemCount(): Int = items.size

}