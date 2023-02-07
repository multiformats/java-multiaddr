package io.ipfs.api;

import io.ipfs.multiaddr.*;
import io.ipfs.multihash.*;
import org.junit.*;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class MultiAddressTest {

    @Test
    public void fails() {
        List<String> parsed = Stream.of(
                "/ip4",
                "/ip4/::1",
                "/ip4/fdpsofodsajfdoisa",
                "/ip6",
                "/udp",
                "/tcp",
                "/sctp",
                "/udp/65536",
                "/tcp/65536",
                "/quic/65536",
                "/onion/9imaq4ygg2iegci7:80",
                "/onion/aaimaq4ygg2iegci7:80",
                "/onion/timaq4ygg2iegci7:0",
                "/onion/timaq4ygg2iegci7:-1",
                "/onion/timaq4ygg2iegci7",
                "/onion/timaq4ygg2iegci@:666",
                "/onion3/9ww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyyd:80",
                "/onion3/vww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyyd7:80",
                "/onion3/vww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyyd:0",
                "/onion3/vww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyyd:-1",
                "/onion3/vww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyyd",
                "/onion3/vww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyy@:666",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA7:80",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA:0",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA:0",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA:-1",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA@:666",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA7:80",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA:0",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA:0",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA:-1",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA@:666",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzu",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzu77",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzu:80",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzuq:-1",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzu@",
                "/udp/1234/sctp",
                "/udp/1234/udt/1234",
                "/udp/1234/utp/1234",
                "/ip4/127.0.0.1/udp/jfodsajfidosajfoidsa",
                "/ip4/127.0.0.1/udp",
                "/ip4/127.0.0.1/tcp/jfodsajfidosajfoidsa",
                "/ip4/127.0.0.1/tcp",
                "/ip4/127.0.0.1/ipfs",
                "/ip4/127.0.0.1/ipfs/tcp",
                "/unix"
        ).flatMap(s -> {
            try {
                new MultiAddress(s);
                return Stream.of(s);
            } catch (Exception e) {
                return Stream.empty();
            }
        }).collect(Collectors.toList());
        if (parsed.size() > 0)
            throw new IllegalStateException("Parsed invalid MultiAddresses: "+parsed);
    }

    @Test
    public void publicIps() {
        List<MultiAddress> pub = Stream.of("/ip4/8.8.8.8")
                .map(MultiAddress::new)
                .collect(Collectors.toList());
        for (MultiAddress addr : pub) {
            Assert.assertTrue(addr.toString() + " is public", addr.isPublic(false));
        }
        List<MultiAddress> priv = Stream.of(
//                "/ip4/100.64.0.0",
                        "/ip4/172.16.0.0",
                        "/ip4/10.0.0.0",
                        "/ip4/169.254.0.0",
                        "/ip4/192.168.0.0",
                        "/ip4/127.0.0.1",
                        "/ip4/0.0.0.0",
//                        "/ip4/192.0.0.0",
//                        "/ip4/192.0.2.0",
//                        "/ip4/192.88.99.0",
//                        "/ip4/198.18.0.0",
//                        "/ip4/198.51.100.0",
//                        "/ip4/203.0.113.0",
//                        "/ip4/224.0.0.0",
//                        "/ip4/240.0.0.0",
//                        "/ip4/255.255.255.255",
                        "/ip6/::1",
//                        "/ip6/fc00::",
//                        "/ip6/ff00::",
                        "/ip6/fe80::"
                ).map(MultiAddress::new)
                .collect(Collectors.toList());
        for (MultiAddress addr : priv) {
            Assert.assertTrue(addr.toString() + " is private", ! addr.isPublic(false));
        }
    }

    @Test
    public void succeeds() {
        List<String> failed = Stream.of(
                "/ip4/1.2.3.4",
                "/ip4/0.0.0.0",
                "/ip4/192.0.2.0/ipcidr/24",
                "/ip6/::1",
                "/ip6/2601:9:4f81:9700:803e:ca65:66e8:c21",
                "/ip6/2601:9:4f81:9700:803e:ca65:66e8:c21/udp/1234/quic",
                "/ip6/2001:db8::/ipcidr/32",
                "/ip6zone/x/ip6/fe80::1",
                "/ip6zone/x%y/ip6/fe80::1",
                "/ip6zone/x%y/ip6/::",
                "/ip6zone/x/ip6/fe80::1/udp/1234/quic",
                "/ip6/::1/udp/4001/quic-v1",
                "/dnsaddr/bootstrap.libp2p.io/ipfs/QmbLHAnMoJPWSCR5Zhtx6BHJX9KiKNN6tpvbUcqanj75Nb",
                "/ip4/127.0.0.1/udp/4001/quic-v1/webtransport/certhash/uEiBwuhj7sQdRxdMIppgEp5kx2XTfYtSUE_8ukOpi8TRPRw/certhash/uEiD8fBEvx4OOkDOdeknjNkpiFWBOEqJYOjwsFdBJobIJNA",
                "/onion/timaq4ygg2iegci7:1234",
                "/onion/timaq4ygg2iegci7:80/http",
                "/onion3/vww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyyd:1234",
                "/onion3/vww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyyd:80/http",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA/http",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA/udp/8080",
                "/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5Vy~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVDKFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrsMSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJz1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA/tcp/8080",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzuq",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzuqzwas",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzuqzwassw",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzuq/http",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzuq/tcp/8080",
                "/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzuq/udp/8080",
                "/udp/0",
                "/tcp/0",
                "/sctp/0",
                "/udp/1234",
                "/tcp/1234",
                "/sctp/1234",
                "/udp/65535",
                "/tcp/65535",
                "/ipfs/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC",
                "/ipfs/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7",
                "/p2p/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC",
                "/p2p/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7",
                "/p2p/bafzbeigvf25ytwc3akrijfecaotc74udrhcxzh2cx3we5qqnw5vgrei4bm",
                "/p2p/12D3KooWCryG7Mon9orvQxcS1rYZjotPgpwoJNHHKcLLfE4Hf5mV",
                "/p2p/k51qzi5uqu5dhb6l8spkdx7yxafegfkee5by8h7lmjh2ehc2sgg34z7c15vzqs",
                "/p2p/bafzaajaiaejcalj543iwv2d7pkjt7ykvefrkfu7qjfi6sduakhso4lay6abn2d5u",
                "/udp/1234/sctp/1234",
                "/udp/1234/udt",
                "/udp/1234/utp",
                "/tcp/1234/http",
                "/tcp/1234/tls/http",
                "/tcp/1234/https",
                "/ipfs/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC/tcp/1234",
                "/ipfs/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7/tcp/1234",
                "/p2p/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC/tcp/1234",
                "/p2p/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7/tcp/1234",
                "/ip4/127.0.0.1/udp/1234",
                "/ip4/127.0.0.1/udp/0",
                "/ip4/127.0.0.1/tcp/1234",
                "/ip4/127.0.0.1/tcp/1234/",
                "/ip4/127.0.0.1/udp/1234/quic",
                "/ip4/127.0.0.1/udp/1234/quic/webtransport",
                "/ip4/127.0.0.1/udp/1234/quic/webtransport/certhash/b2uaraocy6yrdblb4sfptaddgimjmmpy",
                "/ip4/127.0.0.1/udp/1234/quic/webtransport/certhash/b2uaraocy6yrdblb4sfptaddgimjmmpy/certhash/zQmbWTwYGcmdyK9CYfNBcfs9nhZs17a6FQ4Y8oea278xx41",
                "/ip4/127.0.0.1/ipfs/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC",
                "/ip4/127.0.0.1/ipfs/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC/tcp/1234",
                "/ip4/127.0.0.1/ipfs/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7",
                "/ip4/127.0.0.1/ipfs/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7/tcp/1234",
                "/ip4/127.0.0.1/p2p/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC",
                "/ip4/127.0.0.1/p2p/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC/tcp/1234",
                "/ip4/127.0.0.1/p2p/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7",
                "/ip4/127.0.0.1/p2p/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7/tcp/1234",
                "/unix/a/b/c/d/e",
                "/unix/stdio",
                "/ip4/1.2.3.4/tcp/80/unix/a/b/c/d/e/f",
                "/ip4/127.0.0.1/ipfs/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC/tcp/1234/unix/stdio",
                "/ip4/127.0.0.1/ipfs/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7/tcp/1234/unix/stdio",
                "/ip4/127.0.0.1/p2p/QmcgpsyWgH8Y8ajJz1Cu72KnS5uo2Aa2LpzU7kinSupNKC/tcp/1234/unix/stdio",
                "/ip4/127.0.0.1/p2p/k2k4r8oqamigqdo6o7hsbfwd45y70oyynp98usk7zmyfrzpqxh1pohl7/tcp/1234/unix/stdio",
                "/ip4/127.0.0.1/tcp/9090/http/p2p-webrtc-direct",
                "/ip4/127.0.0.1/tcp/127/ws",
                "/ip4/127.0.0.1/tcp/127/ws",
                "/ip4/127.0.0.1/tcp/127/tls",
                "/ip4/127.0.0.1/tcp/127/tls/ws",
                "/ip4/127.0.0.1/tcp/127/noise",
                "/ip4/127.0.0.1/tcp/127/wss",
                "/ip4/127.0.0.1/tcp/127/wss"
        ).flatMap(s -> {
            try {
                new MultiAddress(s);
                return Stream.empty();
            } catch (Exception e) {
                e.printStackTrace();
                return Stream.of(s);
            }
        }).collect(Collectors.toList());
        if (failed.size() > 0)
            throw new IllegalStateException("Failed to construct MultiAddresses: "+failed);
    }

    @Test
    public void equalsTests() {
        MultiAddress m1 = new MultiAddress("/ip4/127.0.0.1/udp/1234");
        MultiAddress m2 = new MultiAddress("/ip4/127.0.0.1/tcp/1234");
        MultiAddress m3 = new MultiAddress("/ip4/127.0.0.1/tcp/1234");
        MultiAddress m4 = new MultiAddress("/ip4/127.0.0.1/tcp/1234/");

        if (m1.equals(m2))
            throw new IllegalStateException("Should be unequal!");

        if (m2.equals(m1))
            throw new IllegalStateException("Should be unequal!");

        if (!m2.equals(m3))
            throw new IllegalStateException("Should be equal!");

        if (!m3.equals(m2))
            throw new IllegalStateException("Should be equal!");

        if (!m1.equals(m1))
            throw new IllegalStateException("Should be equal!");

        if (!m2.equals(m4))
            throw new IllegalStateException("Should be equal!");

        if (!m4.equals(m3))
            throw new IllegalStateException("Should be equal!");
    }

   @Test
    public void conversion() {
        BiConsumer<String, String> test = (s, h) -> {
            if (!s.equals(new MultiAddress(fromHex(h)).toString()))
                throw new IllegalStateException(s + " != " + new MultiAddress(fromHex(h)));
            if (! Arrays.equals(new MultiAddress(s).getBytes(), fromHex(h)))
                throw new IllegalStateException("bytes for " + s + " != " + fromHex(h));
        };

        test.accept("/ip4/159.89.141.29/udp/5491/quic", "049f598d1d91021573cc03");
        test.accept("/ip4/127.0.0.1/udp/1234", "047f000001910204d2");
        test.accept("/ip4/127.0.0.1/tcp/4321", "047f0000010610e1");
        test.accept("/ip4/127.0.0.1/udp/1234/ip4/127.0.0.1/tcp/4321", "047f000001910204d2047f0000010610e1");
        test.accept("/onion/aaimaq4ygg2iegci:80", "bc030010c0439831b48218480050");
        test.accept("/onion3/vww6ybal4bd7szmgncyruucpgfkqahzddi37ktceo3ah7ngmcopnpyyd:1234", "bd03adadec040be047f9658668b11a504f3155001f231a37f54c4476c07fb4cc139ed7e30304d2");
        test.accept("/garlic64/jT~IyXaoauTni6N4517EG8mrFUKpy0IlgZh-EY9csMAk82Odatmzr~YTZy8Hv7u~wvkg75EFNOyqb~nAPg-" +
                "khyp2TS~ObUz8WlqYAM2VlEzJ7wJB91P-cUlKF18zSzVoJFmsrcQHZCirSbWoOknS6iNmsGRh5KVZsBEfp1Dg3gwTipTRIx7Vl5V" +
                "y~1OSKQVjYiGZS9q8RL0MF~7xFiKxZDLbPxk0AK9TzGGqm~wMTI2HS0Gm4Ycy8LYPVmLvGonIBYndg2bJC7WLuF6tVjVquiokSVD" +
                "KFwq70BCUU5AU-EvdOD5KEOAM7mPfw-gJUG4tm1TtvcobrObqoRnmhXPTBTN5H7qDD12AvlwFGnfAlBXjuP4xOUAISL5SRLiulrs" +
                "MSiT4GcugSI80mF6sdB0zWRgL1yyvoVWeTBn1TqjO27alr95DGTluuSqrNAxgpQzCKEWAyzrQkBfo2avGAmmz2NaHaAvYbOg0QSJ" +
                "z1PLjv2jdPW~ofiQmrGWM1cd~1cCqAAAA",
                "be0383038d3fc8c976a86ae4e78ba378e75ec41bc9ab1542a9cb422581987e118f5cb0c024f3639d6ad9b3aff613672f0" +
                        "7bfbbbfc2f920ef910534ecaa6ff9c03e0fa4872a764d2fce6d4cfc5a5a9800cd95944cc9ef0241f753fe71494a1" +
                        "75f334b35682459acadc4076428ab49b5a83a49d2ea2366b06461e4a559b0111fa750e0de0c138a94d1231ed5979" +
                        "572ff53922905636221994bdabc44bd0c17fef11622b16432db3f193400af53cc61aa9bfc0c4c8d874b41a6e1873" +
                        "2f0b60f5662ef1a89c80589dd8366c90bb58bb85ead56356aba2a244950ca170abbd01094539014f84bdd383e4a1" +
                        "0e00cee63dfc3e809506e2d9b54edbdca1bace6eaa119e68573d30533791fba830f5d80be5c051a77c09415e3b8f" +
                        "e3139400848be5244b8ae96bb0c4a24f819cba0488f34985eac741d3359180bd72cafa1559e4c19f54ea8cedbb6a" +
                        "5afde4319396eb92aab340c60a50cc2284580cb3ad09017e8d9abc60269b3d8d687680bd86ce834412273d4f2e3b" +
                        "f68dd3d6fe87e2426ac658cd5c77fd5c0aa000000");
        test.accept("/garlic32/566niximlxdzpanmn4qouucvua3k7neniwss47li5r6ugoertzuq",
                "bf0320efbcd45d0c5dc79781ac6f20ea5055a036afb48d45a52e7d68ec7d4338919e69");
    }

   @Test
    public void dns() {
       String dns = "mydomain.com";
       int port = 5001;
       String address = "/dns6/"+dns+"/tcp/"+port+"/https";
       MultiAddress multiAddress = new MultiAddress(address);
       
       Assert.assertEquals("host should be equal to " + dns, dns, multiAddress.getHost());
       Assert.assertEquals("port should be equal to " + port, port, multiAddress.getPort());
    }

    public static byte[] fromHex(String hex) {
        if (hex.length() % 2 != 0)
            throw new IllegalStateException("Uneven number of hex digits!");
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        for (int i=0; i < hex.length()-1; i+= 2)
            bout.write(Integer.valueOf(hex.substring(i, i+2), 16));
        return bout.toByteArray();
    }
}
