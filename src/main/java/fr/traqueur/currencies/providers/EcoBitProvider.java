package fr.traqueur.currencies.providers;

import com.willfp.ecobits.currencies.Currencies;
import com.willfp.ecobits.currencies.Currency;
import com.willfp.ecobits.currencies.CurrencyUtils;
import fr.traqueur.currencies.CurrencyProvider;
import org.bukkit.OfflinePlayer;

import java.math.BigDecimal;

public class EcoBitProvider implements CurrencyProvider {

    private Currency currency;
    private final String currencyName;

    public EcoBitProvider(String currencyName) {
        this.currencyName = currencyName;
    }

    private void initialize() {
        if (currency == null) {
            this.currency = Currencies.getByID(currencyName);
        }
    }

    @Override
    public void deposit(OfflinePlayer offlinePlayer, BigDecimal amount, String reason) {
        initialize();
        CurrencyUtils.adjustBalance(offlinePlayer, currency, amount);
    }

    @Override
    public void withdraw(OfflinePlayer offlinePlayer, BigDecimal amount, String reason) {
        initialize();
        CurrencyUtils.adjustBalance(offlinePlayer, currency, amount.negate());
    }

    @Override
    public BigDecimal getBalance(OfflinePlayer offlinePlayer) {
        initialize();
        return CurrencyUtils.getBalance(offlinePlayer, currency);
    }
}
