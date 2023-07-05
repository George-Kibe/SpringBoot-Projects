import React from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
} from 'react-native';
import {Colors} from 'react-native/Libraries/NewAppScreen';
import FontAwesome from 'react-native-vector-icons/FontAwesome';
function App() {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <View
        contentInsetAdjustmentBehavior="automatic"
        style={styles.container}
      />
      <Text>Hello</Text>
      <FontAwesome name="phone" />
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    marginTop: 32,
    paddingHorizontal: 24,
    display: 'flex',
    flex: 1,
  },
});

export default App;
