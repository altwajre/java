package com.company.app;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

class Asset{
    public enum AssetType {BOND, STOCK};
    private final AssetType type;
    private final int value;
    Asset(final AssetType type, final int value) {
        this.type = type;
        this.value = value;
    }
    public AssetType getType() {return type;}
    public int getValue() {return value;}
}
class AssetUtil {
    public static int totalAssetValues(final List<Asset> assets){
        return assets.stream()
                .mapToInt(Asset::getValue)
                .sum();
    }
    public static int totalBondValues(final List<Asset> assets){
        return assets.stream()
                .mapToInt(a -> a.getType() == Asset.AssetType.BOND ? a.getValue() : 0)
                .sum();
    }
    public static int totalStockValues(final List<Asset> assets){
        return assets.stream()
                .mapToInt(a -> a.getType() == Asset.AssetType.STOCK ? a.getValue() : 0)
                .sum();
    }
}
class AssetUtilStrategy{
    public static int totalAssetValues(final List<Asset> assets, final Predicate<Asset> assetSelector){
        return assets.stream()
                .filter(assetSelector)
                .mapToInt(Asset::getValue)
                .sum();
    }
}
public class App
{
    final static List<Asset> assets = Arrays.asList(
            new Asset(Asset.AssetType.BOND, 1000),
            new Asset(Asset.AssetType.BOND, 2000),
            new Asset(Asset.AssetType.STOCK, 3000),
            new Asset(Asset.AssetType.STOCK, 4000)
    );
    public static void main( String[] args )
    {
        System.out.println("#dupLambdasVersion");
        dupLambdasVersion();
        System.out.println("#strategyPatternVersion");
        strategyPatternVersion();
    }

    private static void strategyPatternVersion() {
        System.out.println("Total of all assets: " + AssetUtilStrategy.totalAssetValues(assets, a -> true));
        System.out.println("Total of bonds: " +
                AssetUtilStrategy.totalAssetValues(assets, a -> a.getType() == Asset.AssetType.BOND));
        System.out.println("Total of stocks: " +
                AssetUtilStrategy.totalAssetValues(assets, a -> a.getType() == Asset.AssetType.STOCK));
    }

    private static void dupLambdasVersion() {
        System.out.println("Total of all assets: " + AssetUtil.totalAssetValues(assets));
        System.out.println("Total of bonds: " + AssetUtil.totalBondValues(assets));
        System.out.println("Total of stocks: " + AssetUtil.totalStockValues(assets));
    }
}
/*
output:
#dupLambdasVersion
Total of all assets: 10000
Total of bonds: 3000
Total of stocks: 7000
#strategyPatternVersion
Total of all assets: 10000
Total of bonds: 3000
Total of stocks: 7000
 */
