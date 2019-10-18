<template>
  <div class="box_select" :class="{selected: !!selectedOption, selected_tf: isSelectedCustomValue}">
    <span class="txt_select">{{selectedLabel}}</span>
    <div class="tf_select">
      <textbox
        type="text"
        ref="input"
        class="inp_comm"
        v-model="customValue"
        :maxlength="maxlength"
        placeholder="직접입력"
      />
    </div>
    <select v-model="selectedValue" @change="select">
      <option :value="null" v-if="!!placeholder">{{placeholder}}</option>
      <option v-for="(item, index) in selectOptions" :value="item.value" :key="index">{{item.label}}</option>
    </select>
    <span class="ico_delivery ico_select">펼치기</span>
  </div>
</template>
<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import Textbox from '~/components/global/Textbox.vue';

interface IOption {
  value: string | number;
  label: string;
}

const CUSTOM_VALUE = '직접 입력';

@Component
export default class CustomSelectbox extends Vue {
  $refs!: {
    input: Textbox;
  };

  @Prop()
  options!: IOption[];

  @Prop()
  value!: string | number;

  @Prop()
  placeholder!: string;

  @Prop()
  hasCustom!: boolean;

  @Prop()
  maxlength!: string;

  @Prop()
  event!: string;

  selectedValue: string | number = '';
  customValue: string = '';

  get isSelectedCustomValue() {
    return this.selectedValue === CUSTOM_VALUE;
  }

  get selectOptions() {
    const options = [...(this.options || [])];
    if (options.length && this.hasCustom) {
      options.push({
        value: CUSTOM_VALUE,
        label: CUSTOM_VALUE,
      });
    }
    return options;
  }

  get selectedOption() {
    return this.selectOptions.find(({ value }) => value === this.selectedValue);
  }

  get selectedLabel() {
    return this.selectedOption ? this.selectedOption.label : this.placeholder || '';
  }

  @Watch('value')
  onChangeValue() {
    this.updateValue(this.value);
  }

  @Watch('options')
  onChangeOptions() {
    this.updateValue(this.value);
  }

  @Watch('selectedValue')
  onSelectedValue() {
    const value = this.isSelectedCustomValue ? this.customValue : this.selectedValue;
    this.$emit('input', value);
  }

  @Watch('customValue')
  onChangeCustomValue() {
    this.$emit('input', this.customValue);
  }

  public mounted() {
    this.updateValue(this.value);
  }

  private select(e: Event) {
    if (this.selectedValue === CUSTOM_VALUE) {
      setTimeout(() => {
        this.$refs.input.autoFocus();
      });
    }
  }

  private updateValue(value?: string | number) {
    if (this.isCustomValue(value)) {
      this.selectedValue = CUSTOM_VALUE;
      this.customValue = value as string;
    } else {
      this.selectedValue = value!;
    }
  }

  private isCustomValue(value?: string | number) {
    return (
      this.isSelectedCustomValue ||
      (value &&
        this.selectOptions.length &&
        !this.selectOptions.find(({ value: optionValue }) => optionValue === value))
    );
  }
}
</script>
<style lang="scss" scoped>
input::placeholder {
  color: #b2b2b2;
}
</style>