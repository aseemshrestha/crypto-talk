package com.cryptotalk.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import com.cryptotalk.models.QuoteModel;
import com.cryyptotalk.generated.CoinMarketCap;

@Service
public class LoadersFacade
{
    private final DataLoaderService loaderService;
    private static final Logger LOG = LogManager.getLogger(LoadersFacade.class);

    public LoadersFacade(DataLoaderService loaderService)
    {
        this.loaderService = loaderService;
    }

    public int runLoaders()
    {
        Optional<Map<String, List<QuoteModel>>> binmap = null;
        Optional<Map<String, List<QuoteModel>>> crypmap = null;
        Optional<Map<String, List<QuoteModel>>> bitmap = null;
        Optional<Map<String, List<CoinMarketCap>>> cmcmap = null;

        long start = System.currentTimeMillis();
        try {
            binmap = this.loaderService.loadBinanceData();
            crypmap = this.loaderService.loadCryptopiaData();
            bitmap = this.loaderService.loadBittrexData();
            cmcmap = this.loaderService.loadCoinMarketCapData();

        } catch (Exception ex) {
            LOG.debug("Exception while running loaders:", ex);

        }
        LOG.info("[GetMarkets] time taken to get data--->:" + (System.currentTimeMillis() - start));
        return binmap.get().get("bi_quote").size() + crypmap.get().get("cr_quote").size()
            + bitmap.get().get("bt_quote").size() + cmcmap.get().get("cmc_quote").size();

    }

}
