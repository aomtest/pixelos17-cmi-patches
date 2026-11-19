# PixelOS 17 Upstream & Third-Party Patches for Xiaomi 10 Pro (`cmi` / `sm8250`)

Upstream LineageOS / Gerrit / third-party device-tree & kernel patches referenced or integrated for **Xiaomi 10 Pro (`cmi`)** on top of LineageOS `sm8250` trees.

Companion kernel source fork (GPLv2):  
👉 **[aomtest/android_kernel_xiaomi_sm8250 (`lineage-24.0-cmi-opt`)](https://github.com/aomtest/android_kernel_xiaomi_sm8250/tree/lineage-24.0-cmi-opt)**

---

## Repository Structure

```text
.
├── 00-baseline/                    # Upstream UDFPS sub-HAL wiring, DSPVolumeSynchronizer & tree bringup diffs
│   ├── device_xiaomi_cmi.diff
│   ├── device_xiaomi_sm8250-common.diff
│   ├── device_xiaomi_cmi_extra/    # configs/sensors/hals.conf
│   └── hardware_xiaomi_DSPVolumeSynchronizer/
├── 01-lineage-erofs/               # LineageOS Gerrit & EROFS / display / charging / sepolicy patches
│   ├── 510076-vulkan-ui.patch
│   ├── 511472-charging-bypass.patch
│   ├── 514180-soundtrigger-typo.patch
│   ├── 69f7d5f8-palm-sepolicy.patch
│   ├── afcd876a-display-tx.patch
│   └── sm8250-common-tonight-combined.diff
└── 02-kernel-patches/              # Kernel patches mirrored from the lineage-24.0-cmi-opt branch
    ├── 00-511471-charging-bypass.patch
    └── 0001-cmi-opt_v2_pure-BORE-5.1.0r2-MGLRU-lz4armv8-zstd-1.5.patch
```

---

## Key Components

### 1. Device Tree Baseline & Third-Party Integrations (`00-baseline/`)
- **Screen-off UDFPS Sensor Sub-HAL** (`Luffitys`): Added `sensors/hals.conf` (`sensors.udfps.so`), `kona.mk` package wiring, `hal_sensors_default.te` `sysfs_touchpanel` permissions, and `config_dozeUdfpsLongPressSensorType`.
- **`DSPVolumeSynchronizer`**: Restores stock speaker dynamic protection synchronization on `hardware/xiaomi`.
- **PixelOS Product Bringup**: `AndroidProducts.mk` / `custom_cmi.mk` product makefile adaptation.

### 2. LineageOS / EROFS & Display Fixes (`01-lineage-erofs/`)
- Vulkan UI renderer selection (`kdrag0n`).
- Charging bypass sysfs control (`DIYgod`).
- SoundTrigger debug property typo & palm sensor sepolicy fixes (`Afifmomin`).
- `DisplayManagerService`: Do not defer unknown displays' transactions (`LuK1337`).
- Combined `sm8250-common` upstream adjustments (including `BoardConfigCommon.mk`, `fstab.qcom` EROFS support, and `sepolicy` updates).

### 3. Kernel Performance & Features (`02-kernel-patches/` & Kernel Fork)
Published cleanly on **[aomtest/android_kernel_xiaomi_sm8250 (`lineage-24.0-cmi-opt`)](https://github.com/aomtest/android_kernel_xiaomi_sm8250/tree/lineage-24.0-cmi-opt)** (GPLv2):
- **Screen-off UDFPS `fp_state` sysfs** (`Ivan Vecera`, `UP5468-GE-ES44AC`, `Rocky7842`)
- **Charging bypass control** (`DIYgod`)
- **BORE 5.1.0r2 CPU scheduler** (`firelzrd`)
- **Multi-Gen LRU (MGLRU)** backport
- **ARM64 NEON `lz4armv8`** & **Zstd `1.5.3`**
- **CAKE QoS** & **TCP BBR**

---

## Upstream Credits & Licenses

This repository builds upon and strictly respects the work of open-source authors and upstream projects:
- **[LineageOS](https://github.com/LineageOS)** (`android_kernel_xiaomi_sm8250`, `android_device_xiaomi_sm8250-common`, `android_device_xiaomi_cmi`, `android_hardware_xiaomi`)
- **[PixelOS](https://github.com/PixelOS-AOSP)** (ROM base)
- **BORE Scheduler**: [firelzrd/bore-scheduler](https://github.com/firelzrd/bore-scheduler)
- **Screen-off UDFPS / Kernel**: Luffitys, Ivan Vecera, UP5468-GE-ES44AC, Rocky7842
- **LineageOS / Common Contributors**: DIYgod, kdrag0n, Afifmomin, LuK1337

All Linux kernel patches remain licensed under **GPL-2.0**; device-tree, SEPolicy, and Android HAL patches follow their respective upstream licenses (primarily **Apache-2.0**).
