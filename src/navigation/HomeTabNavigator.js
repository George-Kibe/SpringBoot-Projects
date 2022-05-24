import { createBottomTabNavigator } from "@react-navigation/bottom-tabs"
import HomeScreen from '../screens/Home'
import SearchResultsScreen from '../screens/SearchResults';
import ExploreNavigator from "./ExploreNavigator"
import SearchResultsMap from "../screens/SearchResultsMap"
import ProfileScreen from "../screens/Profile"
//Home Screen Icons
import Fontisto from 'react-native-vector-icons/Fontisto'
import FontAwesome from 'react-native-vector-icons/FontAwesome';
import FontAwesome5 from 'react-native-vector-icons/FontAwesome5'
import Feather from 'react-native-vector-icons/Feather'
import EvilIcons from 'react-native-vector-icons/EvilIcons'


const Tab = createBottomTabNavigator()


const HomeTabNavigator = () => {
  return (
    <Tab.Navigator screenOptions={{
        "tabBarActiveTintColor": "#f15454",
        "tabBarStyle": [{ "display": "flex" },null]
    }}>
      <Tab.Screen name={"Explore"} component={ExploreNavigator}
        options={{
            tabBarIcon: ({color}) =>(<Fontisto name="search" size={25} color={color} />)
            }}
      />
      <Tab.Screen name={"Saved"} component={SearchResultsScreen}
        options={{
            tabBarIcon: ({color}) =>(<FontAwesome name="heart" size={25} color={color} />)
            }}
      />
      <Tab.Screen name={"Buenas"} component={SearchResultsMap}
        options={{
            tabBarIcon: ({color}) =>(<FontAwesome5 name="building" size={25} color={color} />)
            }}
      />
      <Tab.Screen name={"Messages"} component={HomeScreen}
        options={{
            tabBarIcon: ({color}) =>(<Feather name="message-square" size={25} color={color} />)
            }}
      />
      <Tab.Screen name={"Profile"} component={ProfileScreen}
        options={{
            tabBarIcon: ({color}) =>(<EvilIcons name="user" size={25} color={color} />)
            }}
      />
    </Tab.Navigator>
  )
}

export default HomeTabNavigator