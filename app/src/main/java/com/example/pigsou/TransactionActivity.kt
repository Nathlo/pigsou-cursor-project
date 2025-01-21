package com.example.pigsou

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class TransactionActivity : AppCompatActivity() {

    private var walletTotal = 1000 // participation amount
    private var userWallet = 230
    private lateinit var totalBalanceTextView: TextView
    private lateinit var noemieBalanceTextView: TextView
    private val walletTitle = "Participation"
    private val userName = "Noémie"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transaction) // layout loading

        // initialise Toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // add back button
        // supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_custom_back)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Transactions"

        // click on back button management
        toolbar.setNavigationOnClickListener {
            finish() // Back to previous screen
        }

        initializeViews()
        setupButtons()
    }

    private fun initializeViews() {
        totalBalanceTextView = findViewById(R.id.wallet_balance)
        noemieBalanceTextView = findViewById(R.id.user_balance)
        updateBalanceDisplays()
    }

    private fun setupButtons() {
        findViewById<Button>(R.id.deposit_button).setOnClickListener {
            updateWallets(isDeposit = true)
        }

        findViewById<Button>(R.id.withdraw_button).setOnClickListener {
            updateWallets(isDeposit = false)
        }
    }

    private fun updateWallets(isDeposit: Boolean) {
        val amount = 50
        if (isDeposit) {
            userWallet += amount
            walletTotal += amount
        } else {
            userWallet -= amount
            walletTotal -= amount
        }
        updateBalanceDisplays()
    }

    private fun updateBalanceDisplays() {
        totalBalanceTextView.text = getString(R.string.money_box_amount, walletTitle, walletTotal)
        noemieBalanceTextView.text = getString(R.string.user_balance, userName, userWallet)
    }
}